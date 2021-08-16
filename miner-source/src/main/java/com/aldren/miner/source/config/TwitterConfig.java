package com.aldren.miner.source.config;

import com.aldren.miner.source.properties.MinerSourceProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
@AllArgsConstructor
public class TwitterConfig implements SocialConfigurer {

    private MinerSourceProperties sourceProps;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(new TwitterConnectionFactory(sourceProps.getConsumer().getKey(),sourceProps.getConsumer().getSecret()));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return null;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}
