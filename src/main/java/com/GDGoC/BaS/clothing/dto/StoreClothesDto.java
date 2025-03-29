package com.GDGoC.BaS.clothing.dto;

import java.util.List;

public record StoreClothesDto(
        List<StoreHeadDto> heads,
        List<StoreTowelDto> towels,
        List<StoreAccessoryDto> accessories
) {
    public static StoreClothesDto of(List<StoreHeadDto> heads,
                                     List<StoreTowelDto> towels,
                                     List<StoreAccessoryDto> accessories) {
        return new StoreClothesDto(
                heads,
                towels,
                accessories
        );
    }
}
