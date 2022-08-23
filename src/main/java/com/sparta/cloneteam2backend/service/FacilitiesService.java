package com.sparta.cloneteam2backend.service;

import com.sparta.cloneteam2backend.dto.facilities.FacilitiesRequestDto;
import com.sparta.cloneteam2backend.model.Facilities;
import com.sparta.cloneteam2backend.repository.FacilitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilitiesService {


    private final FacilitiesRequestDto facilitiesRequestDto;
    private final FacilitiesRepository facilitiesRepository;

    @Transactional
    public void createFacilities(Long postId, List<String> facilitiesList) throws IllegalAccessException {

        for(Field field : facilitiesRequestDto.getClass().getDeclaredFields()) {
            for(String f : facilitiesList) {
                if(field.getName().equals(f)) {
                    field.setAccessible(true);
                    field.set(facilitiesRequestDto, true);
                }
            }
        }
        Facilities facilities = facilitiesRequestDto.toFacilities(postId);
        facilitiesRepository.save(facilities);
    }
}
