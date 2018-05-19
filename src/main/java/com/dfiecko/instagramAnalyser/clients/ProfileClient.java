package com.dfiecko.instagramAnalyser.clients;

public interface ProfileClient<K, V> {

    V getProfileByUrl(K url);
}
