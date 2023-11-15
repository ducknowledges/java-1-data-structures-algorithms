package com.github.ducknowledges.datastructures.bloomfilter;

public class BloomFilter {
  public int filter_len;
  public long filter;

  public BloomFilter(int f_len) {
    filter_len = f_len;
    filter = 0;
  }

  public int hash1(String str1) {
    int code = 0;
    for(int i = 0; i < str1.length(); i++) {
      code = code * 17 + str1.charAt(i);
    }
    return Math.abs(code % filter_len);
  }

  public int hash2(String str1) {
    int code = 0;
    for(int i = 0; i < str1.length(); i++) {
      code = code * 223 + str1.charAt(i);
    }
    return Math.abs(code % filter_len);
  }

  public void add(String str1) {
    filter |= 1L << hash1(str1);
    filter |= 1L << hash2(str1);
  }

  public boolean isValue(String str1) {
    boolean hasBit1 = (filter >> hash1(str1) & 1) == 1;
    boolean hasBit2 = (filter >> hash2(str1) & 1) == 1;
    return hasBit1 && hasBit2;
  }

  public String toString() {
    return Long.toBinaryString(filter);
  }

}
