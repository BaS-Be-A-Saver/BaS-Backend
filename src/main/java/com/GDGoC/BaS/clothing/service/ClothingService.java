package com.GDGoC.BaS.clothing.service;

import com.GDGoC.BaS.clothing.domain.UserAccessory;
import com.GDGoC.BaS.clothing.domain.UserHead;
import com.GDGoC.BaS.clothing.domain.UserTowel;
import com.GDGoC.BaS.clothing.dto.MyAccessoryDto;
import com.GDGoC.BaS.clothing.dto.MyClothesDto;
import com.GDGoC.BaS.clothing.dto.MyHeadDto;
import com.GDGoC.BaS.clothing.dto.MyTowelDto;
import com.GDGoC.BaS.clothing.dto.StoreAccessoryDto;
import com.GDGoC.BaS.clothing.dto.StoreClothesDto;
import com.GDGoC.BaS.clothing.dto.StoreHeadDto;
import com.GDGoC.BaS.clothing.dto.StoreTowelDto;
import com.GDGoC.BaS.clothing.repository.AccessoryRepository;
import com.GDGoC.BaS.clothing.repository.HeadRepository;
import com.GDGoC.BaS.clothing.repository.TowelRepository;
import com.GDGoC.BaS.clothing.repository.UserAccessoryRepository;
import com.GDGoC.BaS.clothing.repository.UserHeadRepository;
import com.GDGoC.BaS.clothing.repository.UserTowelRepository;
import com.GDGoC.BaS.user.domain.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClothingService {

    private final UserHeadRepository userHeadRepository;
    private final UserTowelRepository userTowelRepository;
    private final UserAccessoryRepository userAccessoryRepository;
    private final HeadRepository headRepository;
    private final TowelRepository towelRepository;
    private final AccessoryRepository accessoryRepository;

    @Transactional(readOnly = true)
    public MyClothesDto getMyClothes(User user) {
        List<MyHeadDto> myHeadDtos = userHeadRepository.findAllByUser(user)
                .stream()
                .map(UserHead::getHead)
                .map(MyHeadDto::of)
                .toList();

        List<MyTowelDto> myTowelDtos = userTowelRepository.findAllByUser(user)
                .stream()
                .map(UserTowel::getTowel)
                .map(MyTowelDto::of)
                .toList();

        List<MyAccessoryDto> myAccessoryDtos = userAccessoryRepository.findAllByUser(user)
                .stream()
                .map(UserAccessory::getAccessory)
                .map(MyAccessoryDto::of)
                .toList();

        return MyClothesDto.of(myHeadDtos, myTowelDtos, myAccessoryDtos);
    }

    @Transactional(readOnly = true)
    public StoreClothesDto getStoreClothes(User user) {
        Set<Byte> boughtHeadIds = userHeadRepository.findAllByUser(user)
                .stream()
                .map(userHead -> userHead.getHead().getHeadId())
                .collect(Collectors.toSet());

        List<StoreHeadDto> storeHeadDtos = headRepository.findAll()
                .stream()
                .map(head -> StoreHeadDto.of(head, boughtHeadIds.contains(head.getHeadId())))
                .toList();

        Set<Byte> boughtTowelIds = userTowelRepository.findAllByUser(user)
                .stream()
                .map(userTowel -> userTowel.getTowel().getTowelId())
                .collect(Collectors.toSet());

        List<StoreTowelDto> storeTowelDtos = towelRepository.findAll()
                .stream()
                .map(towel -> StoreTowelDto.of(towel, boughtTowelIds.contains(towel.getTowelId())))
                .toList();

        Set<Byte> boughtAccessoryIds = userAccessoryRepository.findAllByUser(user)
                .stream()
                .map(userAccessory -> userAccessory.getAccessory().getAccessoryId())
                .collect(Collectors.toSet());

        List<StoreAccessoryDto> storeAccessoryDtos = accessoryRepository.findAll()
                .stream()
                .map(accessory -> StoreAccessoryDto.of(accessory,
                        boughtAccessoryIds.contains(accessory.getAccessoryId())))
                .toList();

        return StoreClothesDto.of(storeHeadDtos, storeTowelDtos, storeAccessoryDtos);
    }
}
