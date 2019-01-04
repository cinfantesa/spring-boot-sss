package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Asset;
import edu.cinfantes.springbootsss.domain.exception.AssetNotFoundException;
import edu.cinfantes.springbootsss.domain.repository.AssetRepository;
import edu.cinfantes.springbootsss.domain.repository.ZoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UnMarkAssetToEvaluateTest {
  @Mock
  private AssetRepository assetRepository;
  @Mock
  private ZoneRepository zoneRepository;

  private UnMarkAssetToEvaluate unMarkAssetToEvaluate;

  @Before
  public void setUp() {
    unMarkAssetToEvaluate = new UnMarkAssetToEvaluate(assetRepository);
  }

  @Test
  public void should_throw_exception_when_asset_not_found() {
    UUID assetId = randomUUID();

    when(assetRepository.findById(assetId)).thenReturn(empty());
    Throwable thrown = catchThrowable(() -> unMarkAssetToEvaluate.execute(singletonList(assetId)));

    assertThat(thrown).isInstanceOf(AssetNotFoundException.class);
  }

  @Test
  public void should_save_asset_when_it_is_only_one() {
    UUID assetId = randomUUID();

    when(assetRepository.findById(assetId)).thenReturn(Optional.of(Asset.builder().id(assetId).build()));
    unMarkAssetToEvaluate.execute(singletonList(assetId));

    Asset expectedAssert = Asset.builder()
      .id(assetId)
      .build();

    verify(assetRepository, times(1)).saveAll(singletonList(expectedAssert));
    verify(assetRepository, times(1)).findById(assetId);
    verifyNoMoreInteractions(assetRepository, zoneRepository);
  }

  @Test
  public void should_save_multiple_assets() {
    UUID assetId1 = randomUUID();
    UUID assetId2 = randomUUID();

    when(assetRepository.findById(assetId1)).thenReturn(Optional.of(Asset.builder().id(assetId1).build()));
    when(assetRepository.findById(assetId2)).thenReturn(Optional.of(Asset.builder().id(assetId2).build()));
    unMarkAssetToEvaluate.execute(asList(assetId1, assetId2));

    Asset expectedAssert1 = Asset.builder()
      .id(assetId1)
      .build();

    Asset expectedAssert2 = Asset.builder()
      .id(assetId2)
      .build();

    verify(assetRepository, times(1)).findById(assetId1);
    verify(assetRepository, times(1)).findById(assetId2);
    verify(assetRepository, times(1)).saveAll(asList(expectedAssert1, expectedAssert2));
    verifyNoMoreInteractions(assetRepository, zoneRepository);
  }
}
