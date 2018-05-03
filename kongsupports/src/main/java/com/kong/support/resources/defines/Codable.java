package com.kong.support.resources.defines;

/**
 * 可编解码的
 */
public interface Codable {

    public byte[] encoding(String origin);

    public String decoding(byte[] encoding);
}
