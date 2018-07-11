package ru.erus.nettr.net;

import ru.erus.nettr.data.DataBundle;

public interface OnRecievingCompleted {

    void onComplete(int result, DataBundle dataBundle);

}
