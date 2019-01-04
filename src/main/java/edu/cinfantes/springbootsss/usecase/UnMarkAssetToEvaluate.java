package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Asset;
import edu.cinfantes.springbootsss.domain.exception.AssetNotFoundException;
import edu.cinfantes.springbootsss.domain.repository.AssetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UnMarkAssetToEvaluate {
  private final AssetRepository assetRepository;

  public void execute(List<UUID> assetsId) {
    List<Asset> assets = assetsId.stream()
      .map(this::getAssetWithZone)
      .collect(Collectors.toList());

    assetRepository.saveAll(assets);
  }

  private Asset getAssetWithZone(UUID assetId) {
    Asset asset = findAsset(assetId);
    asset.setZone(null);

    return asset;
  }

  private Asset findAsset(UUID assetId) {
    return assetRepository.findById(assetId)
        .orElseThrow(AssetNotFoundException::new);
  }
}
