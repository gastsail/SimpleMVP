package com.gaston.macbook.kotlinsimplemvp.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper

abstract class BasePresenter protected constructor() {

    @CallSuper
    internal fun onCreate(savedInstanceState: Bundle?) {
    }

    @CallSuper
    internal fun onResume() {
    }

    @CallSuper
    internal fun onPause() {
    }

    @CallSuper
    internal fun onSaveInstanceState(outState: Bundle) {
    }

    @CallSuper
    internal fun onDestroy() {
    }

    @CallSuper
    internal fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    @CallSuper
    internal fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
    }


}