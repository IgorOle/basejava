package com.igorole.basejava.webapp;

import com.igorole.basejava.webapp.storage.ObjectStreamPathStorage;
import com.igorole.basejava.webapp.storage.ObjectStreamStorage;

import java.io.File;

public class ContextStorageTest {
    public static void main(String[] args) {
        ContextStorage contextStorage = new ContextStorage();

        contextStorage.setStorage(new ObjectStreamPathStorage("./strg"));
        contextStorage.save(GenerateResume.genResume("NOI"));

        contextStorage.setStorage(new ObjectStreamStorage(new File("./strg")));
        contextStorage.save(GenerateResume.genResume("IO"));

    }
}
