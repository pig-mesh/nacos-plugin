/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.plugin.encryption;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.nacos.api.utils.StringUtils;
import com.alibaba.nacos.plugin.encryption.spi.EncryptionPluginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * AES Encryption algorithm implementation.
 *
 * @author lixiaoshuang
 */
@SuppressWarnings("PMD.ServiceOrDaoClassShouldEndWithImplRule")
public class SM4EncryptionPluginService implements EncryptionPluginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SM4EncryptionPluginService.class);

    private static final String DEFAULT_SECRET_KEY = "nacos6b31e19f931a7603ae5473250b4";

    @Override
    public String encrypt(String secretKey, String content) {
        if (StringUtils.isBlank(secretKey)) {
            return content;
        }
        try {
            SymmetricCrypto sm4 = new SymmetricCrypto("SM4", secretKey.getBytes(StandardCharsets.UTF_8));
            return sm4.encryptHex(content);
        } catch (Exception e) {
            LOGGER.error("[SM4EncryptionPluginService] encrypt error", e);
        }
        return content;
    }

    @Override
    public String decrypt(String secretKey, String content) {
        if (StringUtils.isBlank(secretKey) || StringUtils.isBlank(content)) {
            return content;
        }
        try {
            SymmetricCrypto sm4 = new SymmetricCrypto("SM4", secretKey.getBytes(StandardCharsets.UTF_8));
            return sm4.decryptStr(content);
        } catch (Exception e) {
            LOGGER.error("[SM4EncryptionPluginService] decrypt error", e);
        }
        return content;
    }

    @Override
    public String generateSecretKey() {
        return DEFAULT_SECRET_KEY;
    }


    @Override
    public String algorithmName() {
        return "sm4";
    }

    @Override
    public String encryptSecretKey(String secretKey) {
        return secretKey;
    }

    @Override
    public String decryptSecretKey(String secretKey) {
        return secretKey;
    }
}
