/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.deploy.deployer;

import java.io.File;

/**
 *
 * @author lyhcode
 */
public interface KoobeDeployer {
    public void deploy(String warFileName);
    public void deploy(File warFile);
    
    public void debug();
}
