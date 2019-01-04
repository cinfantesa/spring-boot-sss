package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Asset;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.exception.AssetNotFoundException;
import edu.cinfantes.springbootsss.domain.exception.ZoneNotFoundException;
import edu.cinfantes.springbootsss.domain.repository.AssetRepository;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MarkAssetToEvaluate {
  private final AssetRepository assetRepository;
  private final ZoneRepository zoneRepository;

  public void execute(List<UUID> assetsId, UUID zoneId) {
    assertZoneExists(zoneId);

    List<Asset> assets = assetsId.stream()
      .map(assetId -> getAssetWithZone(assetId, zoneId))
      .collect(Collectors.toList());

    assetRepository.saveAll(assets);
  }

  private Asset getAssetWithZone(UUID assetId, UUID zoneId) {
    Asset asset = findAsset(assetId);
    asset.setZone(Zone.builder().id(zoneId).build());

    return asset;
  }

  private void assertZoneExists(UUID zoneId) {
    if (!zoneRepository.existsById(zoneId)) {
      throw new ZoneNotFoundException();
    }
  }

  private Asset findAsset(UUID assetId) {
    return assetRepository.findById(assetId)
        .orElseThrow(AssetNotFoundException::new);
  }
}
