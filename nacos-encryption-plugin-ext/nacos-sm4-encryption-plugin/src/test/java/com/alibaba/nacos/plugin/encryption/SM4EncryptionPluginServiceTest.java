/*
 *   Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.alibaba.nacos.plugin.encryption;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * AesEncryptionPluginServiceTest.
 *
 * @author lixiaoshuang
 */
public class SM4EncryptionPluginServiceTest {

    private SM4EncryptionPluginService sm4EncryptionPluginService;

    private static final String CONTENT = "nacos";

    @Before
    public void setUp() throws Exception {
        sm4EncryptionPluginService = new SM4EncryptionPluginService();
    }

    @Test
    public void testEncrypt() {
        String secretKey = sm4EncryptionPluginService.generateSecretKey();
        String encrypt = sm4EncryptionPluginService.encrypt(secretKey, CONTENT);
        Assert.assertNotNull(encrypt);
    }

    @Test
    public void testDecrypt() {
        String secretKey = sm4EncryptionPluginService.generateSecretKey();
        String encrypt = sm4EncryptionPluginService.encrypt(secretKey, CONTENT);
        String decrypt = sm4EncryptionPluginService.decrypt(secretKey, encrypt);
        Assert.assertNotNull(decrypt);
    }

    @Test
    public void testGenerateSecretKey() {
        String secretKey = sm4EncryptionPluginService.generateSecretKey();
        Assert.assertNotNull(secretKey);
    }

    @Test
    public void testNamed() {
        String named = sm4EncryptionPluginService.algorithmName();
        Assert.assertEquals(named, "sm4");
    }
}
