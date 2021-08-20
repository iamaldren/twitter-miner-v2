package com.aldren.miner.processor.service.impl;

import com.aldren.miner.processor.entity.User;
import com.aldren.miner.processor.repository.UserRepository;
import com.aldren.miner.processor.service.UserCacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class UserCacheServiceImpl implements UserCacheService {

    private UserRepository userRepository;

    @Override
    public boolean isUserExceedThreshold(String user) {
        AtomicBoolean isExceedThreshold = new AtomicBoolean(false);

        userRepository.findById(user)
                .ifPresentOrElse((userEntity) -> {
                    if(userEntity.getThresholdCount() >= 5) {
                        isExceedThreshold.set(true);
                        return;
                    }
                    userEntity.setThresholdCount(userEntity.getThresholdCount()+1);
                }, () -> {
                    User userEntity = new User();
                    userEntity.setId(user);
                    userEntity.setThresholdCount(1);
                });

        return isExceedThreshold.get();
    }

}
