package edu.cinfantes.springbootsss.usecase;

import edu.cinfantes.springbootsss.domain.Asset;
import edu.cinfantes.springbootsss.domain.Zone;
import edu.cinfantes.springbootsss.domain.exception.AssetNotFoundException;
import edu.cinfantes.springbootsss.domain.exception.ZoneNotFoundException;
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
import static java.util.Collections.emptyList;
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
public class MarkAssetToEvaluateTest {
  @Mock
  private AssetRepository assetRepository;
  @Mock
  private ZoneRepository zoneRepository;

  private MarkAssetToEvaluate markAssetToEvaluate;

  @Before
  public void setUp() {
    markAssetToEvaluate = new MarkAssetToEvaluate(assetRepository, zoneRepository);
  }

  @Test
  public void should_throw_exception_when_asset_not_found() {
    UUID assetId = randomUUID();
    UUID zoneId = randomUUID();

    when(assetRepository.findById(assetId)).thenReturn(empty());
    when(zoneRepository.existsById(zoneId)).thenReturn(true);
    Throwable thrown = catchThrowable(() -> markAssetToEvaluate.execute(singletonList(assetId), zoneId));

    assertThat(thrown).isInstanceOf(AssetNotFoundException.class);
  }

  @Test
  public void should_throw_exception_when_zone_not_exist() {
    UUID zoneId = randomUUID();

    when(zoneRepository.existsById(zoneId)).thenReturn(false);
    Throwable thrown = catchThrowable(() -> markAssetToEvaluate.execute(emptyList(), zoneId));

    assertThat(thrown).isInstanceOf(ZoneNotFoundException.class);
  }

  @Test
  public void should_insert_asset_when_it_is_new() {
    UUID assetId = randomUUID();
    UUID zoneId = randomUUID();

    when(assetRepository.findById(assetId)).thenReturn(Optional.of(Asset.builder().id(assetId).build()));
    when(zoneRepository.existsById(zoneId)).thenReturn(true);
    markAssetToEvaluate.execute(singletonList(assetId), zoneId);

    Asset expectedAssert = Asset.builder()
      .id(assetId)
      .zone(Zone.builder().id(zoneId).build())
      .build();

    verify(assetRepository, times(1)).saveAll(singletonList(expectedAssert));
    verify(assetRepository, times(1)).findById(assetId);
    verify(zoneRepository, times(1)).existsById(zoneId);
    verifyNoMoreInteractions(assetRepository, zoneRepository);
  }

  @Test
  public void should_insert_multiple_assets() {
    UUID assetId1 = randomUUID();
    UUID assetId2 = randomUUID();
    UUID zoneId = randomUUID();

    when(assetRepository.findById(assetId1)).thenReturn(Optional.of(Asset.builder().id(assetId1).build()));
    when(assetRepository.findById(assetId2)).thenReturn(Optional.of(Asset.builder().id(assetId2).build()));
    when(zoneRepository.existsById(zoneId)).thenReturn(true);
    markAssetToEvaluate.execute(asList(assetId1, assetId2), zoneId);

    Asset expectedAssert1 = Asset.builder()
      .id(assetId1)
      .zone(Zone.builder().id(zoneId).build())
      .build();

    Asset expectedAssert2 = Asset.builder()
      .id(assetId2)
      .zone(Zone.builder().id(zoneId).build())
      .build();

    verify(assetRepository, times(1)).findById(assetId1);
    verify(assetRepository, times(1)).findById(assetId2);
    verify(assetRepository, times(1)).saveAll(asList(expectedAssert1, expectedAssert2));
    verify(zoneRepository, times(1)).existsById(zoneId);
    verifyNoMoreInteractions(assetRepository, zoneRepository);
  }
}
