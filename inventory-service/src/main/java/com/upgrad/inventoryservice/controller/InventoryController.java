package com.upgrad.inventoryservice.controller;

import com.upgrad.commons.model.Error;
import com.upgrad.commons.model.response.ItemResponse;
import com.upgrad.inventoryservice.model.InventoryException;
import com.upgrad.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/inventory")
public final class InventoryController {

  private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

  private final InventoryService inventoryService;


  @GetMapping("/get-item/{item-id}")
  public ItemResponse getItem(@PathVariable(value = "item-id") final int itemId) {
    try {
      LOG.info("Fetching details for Item={}", itemId);
      return ItemResponse.builder()
          .itemPrice(inventoryService.getPrice(itemId))
          .available(true)
          .build();
    } catch (final InventoryException e) {
      LOG.error("Exception in fetching details for Item={}", itemId, e);
      return ItemResponse.builder()
          .available(false)
          .error(Error.builder().message(e.getMessage()).build())
          .build();
    }
  }
}
