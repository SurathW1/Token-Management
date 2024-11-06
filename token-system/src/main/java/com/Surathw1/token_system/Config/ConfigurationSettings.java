package com.Surathw1.token_system.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class ConfigurationSettings {

    private int TotalTokens;
    private int tokenReleaseRate;
    private int customerRetrievalRate;
    private int maxTokenCapacity;

    //Getters and Setters

    public int getTotalToken() {
        return TotalTokens;
    }
    public void setTotalToken(int TotalTokens) {
        this.TotalTokens = TotalTokens;
    }
    public int getTokenReleaseRate() {
        return tokenReleaseRate;
    }
    public void setTokenReleaseRate(int tokenReleaseRate) {
        this.tokenReleaseRate = tokenReleaseRate;
    }
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
    public int getMaxTokenCapacity() {
        return maxTokenCapacity;
    }
    public void setMaxTokenCapacity(int maxTokenCapacity) {
        this.maxTokenCapacity = maxTokenCapacity;
    }

}
