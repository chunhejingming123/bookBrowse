/*
 * Copyright (C) 2008 ZXing authors
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

package com.wenli.zxlibing.decoding;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import com.wenli.zxlibing.R;
import com.wenli.zxlibing.activity.ScanFragment;
import com.wenli.zxlibing.camera.CameraManager;
import com.wenli.zxlibing.view.ViewfinderResultPointCallback;

import java.util.Vector;


/**
 * This class handles all the messaging which comprises the state machine for capture.
 */
public final class ScannerHandler extends Handler {

    private static final String TAG = ScannerHandler.class.getSimpleName();

    private final ScanFragment fragment;
    private final ScannerDecodeThread decodeThread;
    private State state;
    private boolean isFlag=false;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public ScannerHandler(ScanFragment fragment, Vector<BarcodeFormat> decodeFormats,
                          String characterSet) {
        this.fragment = fragment;
        decodeThread = new ScannerDecodeThread(fragment, decodeFormats, characterSet,
                new ViewfinderResultPointCallback(fragment.getViewfinderView()));
        decodeThread.start();
        state = State.SUCCESS;
        // Start ourselves capturing previews and decoding.
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }


    @Override
    public void handleMessage(Message message) {
        if (message.what == R.id.auto_focus) {
            Log.e("Tag", "--------R.id.auto_focus-----state="+state.name());
            if (state == State.PREVIEW) {
                Log.e("Tag", "--------000000000000----------");
                CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
            }
        } else if (message.what == R.id.restart_preview) {
            Log.e("Tag", "--------1111111111111----------");
            Log.d(TAG, "Got restart preview message");
            restartPreviewAndDecode();
        } else if (message.what == R.id.decode_succeeded) {
            Log.e("Tag", "--------22222222222222----------");
            Bundle bundle = message.getData();
            Bitmap barcode = bundle == null ? null :
                    (Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP);
            fragment.handleDecode((Result) message.obj, barcode);
        } else if (message.what == R.id.decode_failed) {
            Log.e("Tag", "--------33333333333333----------");
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
        } else if (message.what == R.id.return_scan_result) {
            Log.e("Tag", "--------4444444444444444----------");
            Log.d(TAG, "Got return scan result message");
            fragment.getActivity().setResult(Activity.RESULT_OK, (Intent) message.obj);
        } else if (message.what == R.id.launch_product_query) {
            Log.e("Tag", "--------555555555555555----------");
            Log.d(TAG, "Got product query message");
            String url = (String) message.obj;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            fragment.startActivity(intent);
        }
    }

    public void quitSynchronously() {
        state = State.DONE;
        CameraManager.get().stopPreview();
        Message quit = Message.obtain(decodeThread.getHandler(), R.id.quit);
        quit.sendToTarget();
        try {
            decodeThread.join();
        } catch (InterruptedException e) {
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
            CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
            fragment.drawViewfinder();
        }
    }

}
