package edu.cinfantes.springbootsss.persistence;

import edu.cinfantes.springbootsss.domain.Asset;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.repository.AssetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class AssetRepositoryImpl implements AssetRepository {
  private final SpringAssetRepository springAssetRepository;

  @Override
  public Optional<Asset> findById(UUID assetId) {
    return springAssetRepository.findById(assetId)
      .map(this::convertToAsset);
  }

  @Override
  public void saveAll(Collection<Asset> assets) {
    List<AssetEntity> entities = assets.stream()
      .map(this::convertToEntity)
      .collect(Collectors.toList());

    springAssetRepository.saveAll(entities);
  }

  private Asset convertToAsset(AssetEntity entity) {
    Asset asset = Asset.builder()
      .id(entity.getId())
      .name(entity.getName())
      .build();

    if (nonNull(entity.getZone())) {
      asset.setZone(Zone.builder()
        .id(entity.getZone().getId())
        .build());
    }

    return asset;
  }

  private AssetEntity convertToEntity(Asset asset) {
    AssetEntity entity = AssetEntity.builder()
      .id(asset.getId())
      .name(asset.getName())
      .build();

    if (nonNull(asset.getZone())) {
      entity.setZone(ZoneEntity.builder()
        .id(asset.getZone().getId())
        .build());
    }

    return entity;
  }
}
