package com.koobe.common.data.security;

/**
 * Security Manager Interface
 */
public interface DatabaseSecurityManager {
    void allow(String securityGroupName, String address);
    void deny(String securityGroupName, String address);
}
