package edu.cinfantes.springbootsss.rest;

import edu.cinfantes.springbootsss.usecase.MarkAssetToEvaluate;
import edu.cinfantes.springbootsss.usecase.UnMarkAssetToEvaluate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.util.Collections.singletonList;

@RestController
@AllArgsConstructor
@RequestMapping("/asset")
public class AssetController {
  private final MarkAssetToEvaluate markAssetToEvaluate;
  private final UnMarkAssetToEvaluate unMarkAssetToEvaluate;

  @PutMapping("/{id}/zone/{zoneId}/mark")
  public void mark(@PathVariable("id") UUID id, @PathVariable("zoneId") UUID zoneId) {
    markAssetToEvaluate.execute(singletonList(id), zoneId);
  }

  @PutMapping("/{id}/unmark")
  public void unmark(@PathVariable("id") UUID id) {
    unMarkAssetToEvaluate.execute(singletonList(id));
  }
}
