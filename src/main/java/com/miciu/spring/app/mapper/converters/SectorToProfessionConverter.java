package com.miciu.spring.app.mapper.converters;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.miciu.spring.app.model.Profession;
import com.miciu.spring.app.model.Sector;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import static com.miciu.spring.app.model.Profession.*;
import static com.miciu.spring.app.model.Sector.*;

public class SectorToProfessionConverter extends BidirectionalConverter<Sector, Profession> {

  private static final BiMap<Sector, Profession> MAP =
      new ImmutableBiMap.Builder<Sector, Profession>()
          .put(FINANCE, BANKER)
          .put(HEALTH, NURSE)
          .put(MILITARY, SOLIDER)
          .put(TRANSPORTATION, BUS_DRIVER)
          .build();

  @Override
  public Profession convertTo(Sector sector, Type<Profession> type, MappingContext mappingContext) {
    return MAP.get(sector);
  }

  @Override
  public Sector convertFrom(Profession profession, Type<Sector> type, MappingContext mappingContext) {
    return MAP.inverse().get(profession);
  }
}
