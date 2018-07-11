package ru.erus.nettr.net;

import ru.erus.nettr.Data.DataBundle;

public interface OnRecievingCompleted {

    void onComplete(int result, DataBundle dataBundle);

}
