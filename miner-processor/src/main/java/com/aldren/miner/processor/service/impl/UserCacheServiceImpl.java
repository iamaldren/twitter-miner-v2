package com.aldren.miner.processor.service.impl;

import com.aldren.miner.processor.entity.User;
import com.aldren.miner.processor.properties.PolarityProperties;
import com.aldren.miner.processor.repository.UserRepository;
import com.aldren.miner.processor.service.UserCacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@AllArgsConstructor
public class UserCacheServiceImpl implements UserCacheService {

    private UserRepository userRepository;
    private PolarityProperties polarityProperties;

    @Override
    public boolean isUserExceedThreshold(String user) {
        AtomicBoolean isExceedThreshold = new AtomicBoolean(false);

        userRepository.findById(user)
                .ifPresentOrElse((userEntity) -> {
                    if(userEntity.getThresholdCount() >= polarityProperties.getUserThreshold()) {
                        log.info(String.format("Threshold exceeded for %s.", user));
                        isExceedThreshold.set(true);
                        return;
                    }
                    userEntity.setThresholdCount(userEntity.getThresholdCount()+1);
                }, () -> {
                    log.info(String.format("User %s doesn't exists in the cache.", user));
                    User userEntity = new User();
                    userEntity.setId(user);
                    userEntity.setThresholdCount(1);

                    userRepository.save(userEntity);
                });

        return isExceedThreshold.get();
    }

}
