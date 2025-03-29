package com.GDGoC.BaS.clothing.dto;

import java.util.List;

public record MyClothesDto(
        List<MyHeadDto> heads,
        List<MyTowelDto> towels,
        List<MyAccessoryDto> accessories
) {
    public static MyClothesDto of(List<MyHeadDto> heads,
                                  List<MyTowelDto> towels,
                                  List<MyAccessoryDto> accessories) {
        return new MyClothesDto(
                heads,
                towels,
                accessories
        );
    }
}
