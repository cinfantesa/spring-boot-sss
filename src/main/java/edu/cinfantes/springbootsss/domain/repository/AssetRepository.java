package edu.cinfantes.springbootsss.domain.repository;

import edu.cinfantes.springbootsss.domain.Asset;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface AssetRepository {
  Optional<Asset> findById(UUID assetId);

  void saveAll(Collection<Asset> assets);
}
