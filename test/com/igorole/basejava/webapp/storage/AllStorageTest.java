package com.igorole.basejava.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                ListStorageTest.class,
                MapUUIDStorageTest.class,
                MapStorageTest.class,
                ObjectStreamStorage.class,
                ObjectStreamPathStorage.class
        })
public class AllStorageTest {
}