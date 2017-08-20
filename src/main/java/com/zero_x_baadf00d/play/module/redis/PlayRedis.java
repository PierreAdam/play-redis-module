/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 - 2017 Thibault Meyer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zero_x_baadf00d.play.module.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Redis module give access to methods to easily use
 * a Redis database.
 *
 * @author Thibault Meyer
 * @author Pierre Adam
 * @version 17.08.20
 * @since 16.03.09
 */
public interface PlayRedis {

    /**
     * Get a Redis connection from the pool.
     *
     * @return A Redis connection
     * @see Jedis
     * @since 16.03.09
     */
    Jedis getConnection();

    /**
     * Get a Redis connection from the pool pre-configured
     * with the right database. If the database number is
     * under zero, the database "zero" will be selected.
     *
     * @param db The database number to use
     * @return A Redis connection
     * @see Jedis
     * @since 16.05.07
     */
    Jedis getConnection(final int db);

    /**
     * Retrieves an object by key.
     *
     * @param key           Item key
     * @param typeReference The object type reference
     * @param <T>           Generic type of something
     * @return object or {@code null}
     * @since 16.03.09
     */
    <T> T get(final String key, final TypeReference<T> typeReference);

    /**
     * Retrieves an object by key.
     *
     * @param key   Item key
     * @param clazz The object class
     * @param <T>   Generic type of something
     * @return object or {@code null}
     * @since 17.08.20
     */
    <T> T get(final String key, final Class<?> clazz);

    /**
     * Retrieves an object by key.
     *
     * @param key      Item key
     * @param javaType The object java type
     * @param <T>      Generic type of something
     * @return object or {@code null}
     * @since 17.08.20
     */
    <T> T get(final String key, final JavaType javaType);

    /**
     * Sets a value without expiration.
     *
     * @param key           Item key
     * @param typeReference The object type reference
     * @param value         The value to set
     * @param <T>           Generic type of something
     * @since 16.03.31
     */
    <T> void set(final String key, final TypeReference<T> typeReference, final Object value);

    /**
     * Sets a value with expiration.
     *
     * @param key           Item key
     * @param typeReference The object type reference
     * @param value         The value to set
     * @param expiration    expiration in seconds
     * @param <T>           Generic type of something
     * @since 16.03.31
     */
    <T> void set(final String key, final TypeReference<T> typeReference, final Object value, final int expiration);

    /**
     * Sets a value without expiration.
     *
     * @param key   Item key
     * @param clazz The object class
     * @param value The value to set
     * @since 17.08.20
     */
    void set(final String key, final Class<?> clazz, final Object value);

    /**
     * Sets a value with expiration.
     *
     * @param key        Item key
     * @param clazz      The object class
     * @param value      The value to set
     * @param expiration expiration in seconds
     * @since 17.08.20
     */
    void set(final String key, final Class<?> clazz, final Object value, final int expiration);

    /**
     * Sets a value without expiration.
     *
     * @param key      Item key
     * @param javaType The object java type
     * @param value    The value to set
     * @since 17.08.20
     */
    void set(final String key, final JavaType javaType, final Object value);

    /**
     * Sets a value with expiration.
     *
     * @param key        Item key
     * @param javaType   The object java type
     * @param value      The value to set
     * @param expiration expiration in seconds
     * @since 17.08.20
     */
    void set(final String key, final JavaType javaType, final Object value, final int expiration);

    /**
     * Retrieve a value from the cache, or set it from a default
     * Callable function. The value has no expiration.
     *
     * @param key           Item key
     * @param typeReference The object type reference
     * @param block         block returning value to set if key does not exist
     * @param <T>           Generic type of something
     * @return value
     * @since 16.03.31
     */
    <T> T getOrElse(final String key, final TypeReference<T> typeReference, final Callable<T> block);

    /**
     * Retrieve a value from the cache, or set it from a default
     * Callable function.
     *
     * @param <T>           Generic type of something implementing {@code java.io.Serializable}
     * @param key           Item key
     * @param typeReference The object type reference
     * @param block         block returning value to set if key does not exist
     * @param expiration    expiration period in seconds
     * @return value
     * @since 16.03.31
     */
    <T> Object getOrElse(final String key, final TypeReference<T> typeReference, final Callable<T> block, final int expiration);

    /**
     * Retrieve a value from the cache, or set it from a default
     * Callable function. The value has no expiration.
     *
     * @param key   Item key
     * @param clazz The object class
     * @param block block returning value to set if key does not exist
     * @param <T>   Generic type of something
     * @return value
     * @since 17.08.20
     */
    <T> T getOrElse(final String key, final Class<?> clazz, final Callable<T> block);

    /**
     * Retrieve a value from the cache, or set it from a default
     * Callable function.
     *
     * @param <T>        Generic type of something implementing {@code java.io.Serializable}
     * @param key        Item key
     * @param clazz      The object class
     * @param block      block returning value to set if key does not exist
     * @param expiration expiration period in seconds
     * @return value
     * @since 17.08.20
     */
    <T> T getOrElse(final String key, final Class<?> clazz, final Callable<T> block, final int expiration);

    /**
     * Retrieve a value from the cache, or set it from a default
     * Callable function. The value has no expiration.
     *
     * @param key      Item key
     * @param javaType The object java type
     * @param block    block returning value to set if key does not exist
     * @param <T>      Generic type of something
     * @return value
     * @since 17.08.20
     */
    <T> T getOrElse(final String key, final JavaType javaType, final Callable<T> block);

    /**
     * Retrieve a value from the cache, or set it from a default
     * Callable function.
     *
     * @param <T>        Generic type of something implementing {@code java.io.Serializable}
     * @param key        Item key
     * @param javaType   The object java type
     * @param block      block returning value to set if key does not exist
     * @param expiration expiration period in seconds
     * @return value
     * @since 17.08.20
     */
    <T> T getOrElse(final String key, final JavaType javaType, final Callable<T> block, final int expiration);

    /**
     * Removes a value from the cache.
     *
     * @param key The key to remove the value for
     * @since 16.03.09
     */
    void remove(final String key);

    /**
     * Removes a value from the cache.
     *
     * @param keys Keys to remove from redis
     * @since 16.03.09
     */
    void remove(final String... keys);

    /**
     * Check if key is present on Redis database.
     *
     * @param key The key to test
     * @return {@code true} if the key is present on Redis database
     * @since 16.04.11
     */
    boolean exists(final String key);

    /**
     * Add a value in a list.
     *
     * @param key           The list key
     * @param typeReference The object type reference
     * @param value         The value to add in the list
     * @param <T>           Generic type of something implementing {@code java.io.Serializable}
     * @since 16.05.09
     */
    <T> void addInList(final String key, final TypeReference<T> typeReference, final Object value);

    /**
     * Add a value in a list.
     *
     * @param key           The list key
     * @param typeReference The object type reference
     * @param value         The value to add in the list
     * @param maxItem       The number of entries to keep in list
     * @param <T>           Generic type of something implementing {@code java.io.Serializable}
     * @since 16.05.09
     */
    <T> void addInList(final String key, final TypeReference<T> typeReference, final Object value, final int maxItem);

    /**
     * Add a value in a list.
     *
     * @param key   The list key
     * @param clazz The object class
     * @param value The value to add in the list
     * @since 17.08.20
     */
    void addInList(final String key, final Class<?> clazz, final Object value);

    /**
     * Add a value in a list.
     *
     * @param key     The list key
     * @param clazz   The object class
     * @param value   The value to add in the list
     * @param maxItem The number of entries to keep in list
     * @since 17.08.20
     */
    void addInList(final String key, final Class<?> clazz, final Object value, final int maxItem);

    /**
     * Add a value in a list.
     *
     * @param key      The list key
     * @param javaType The object java type
     * @param value    The value to add in the list
     * @since 17.08.20
     */
    void addInList(final String key, final JavaType javaType, final Object value);

    /**
     * Add a value in a list.
     *
     * @param key      The list key
     * @param javaType The object java type
     * @param value    The value to add in the list
     * @param maxItem  The number of entries to keep in list
     * @since 17.08.20
     */
    void addInList(final String key, final JavaType javaType, final Object value, final int maxItem);

    /**
     * Get values from a list.
     *
     * @param key           The list key
     * @param typeReference The object type reference
     * @param <T>           Generic type of something implementing {@code java.io.Serializable}
     * @return The values list
     * @since 16.05.09
     */
    <T> List<T> getFromList(final String key, final TypeReference<T> typeReference);

    /**
     * Get values from a list.
     *
     * @param key           The list key
     * @param typeReference The object type reference
     * @param offset        From where
     * @param count         The number of items to retrieve
     * @param <T>           Generic type of something implementing {@code java.io.Serializable}
     * @return The values list
     * @since 16.05.09
     */
    <T> List<T> getFromList(final String key, final TypeReference<T> typeReference, final int offset, final int count);

    /**
     * Get values from a list.
     *
     * @param key   The list key
     * @param clazz The object class
     * @param <T>   Generic type of something implementing {@code java.io.Serializable}
     * @return The values list
     * @since 17.08.20
     */
    <T> List<T> getFromList(final String key, final Class<?> clazz);

    /**
     * Get values from a list.
     *
     * @param key    The list key
     * @param clazz  The object class
     * @param offset From where
     * @param count  The number of items to retrieve
     * @param <T>    Generic type of something implementing {@code java.io.Serializable}
     * @return The values list
     * @since 17.08.20
     */
    <T> List<T> getFromList(final String key, final Class<?> clazz, final int offset, final int count);

    /**
     * Get values from a list.
     *
     * @param key      The list key
     * @param javaType The object java type
     * @param <T>      Generic type of something implementing {@code java.io.Serializable}
     * @return The values list
     * @since 17.08.20
     */
    <T> List<T> getFromList(final String key, final JavaType javaType);

    /**
     * Get values from a list.
     *
     * @param key      The list key
     * @param javaType The object java type
     * @param offset   From where
     * @param count    The number of items to retrieve
     * @param <T>      Generic type of something implementing {@code java.io.Serializable}
     * @return The values list
     * @since 17.08.20
     */
    <T> List<T> getFromList(final String key, final JavaType javaType, final int offset, final int count);

    /**
     * Try to acquire a lock. This method will return {@code false} if
     * can't acquire lock or can't connect to Redis server.
     *
     * @param key        The lock key
     * @param expiration The lock TTL
     * @return {@code true} in case of success, otherwise, {@code false}
     * @since 16.08.09
     */
    boolean tryLock(final String key, final int expiration);

    /**
     * Increment an integer value. It key does not exists, it will be
     * created automatically.
     *
     * @param key The value key
     * @return The incremented value
     * @since 17.02.02
     */
    Long increment(final String key);

    /**
     * Increment an integer value. It key does not exists, it will be
     * created automatically.
     *
     * @param key        The value key
     * @param expiration The value TTL
     * @return The incremented value
     * @since 17.02.02
     */
    Long increment(final String key, final int expiration);
}
