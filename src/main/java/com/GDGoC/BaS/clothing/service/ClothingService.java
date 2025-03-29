package com.GDGoC.BaS.clothing.service;

import com.GDGoC.BaS.clothing.domain.UserAccessory;
import com.GDGoC.BaS.clothing.domain.UserHead;
import com.GDGoC.BaS.clothing.domain.UserTowel;
import com.GDGoC.BaS.clothing.dto.MyAccessoryDto;
import com.GDGoC.BaS.clothing.dto.MyClothesDto;
import com.GDGoC.BaS.clothing.dto.MyHeadDto;
import com.GDGoC.BaS.clothing.dto.MyTowelDto;
import com.GDGoC.BaS.clothing.repository.UserAccessoryRepository;
import com.GDGoC.BaS.clothing.repository.UserHeadRepository;
import com.GDGoC.BaS.clothing.repository.UserTowelRepository;
import com.GDGoC.BaS.user.domain.User;
import java.util.List;
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
}
