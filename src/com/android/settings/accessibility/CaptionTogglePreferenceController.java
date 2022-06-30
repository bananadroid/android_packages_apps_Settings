/*
 * Copyright (C) 2022 The Android Open Source Project
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

package com.android.settings.accessibility;

import static com.android.settings.accessibility.AccessibilityUtil.State.OFF;
import static com.android.settings.accessibility.AccessibilityUtil.State.ON;

import android.content.Context;
import android.provider.Settings;
import android.view.accessibility.CaptioningManager;
import android.widget.Switch;

import androidx.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.widget.SettingsMainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;

/** Preference controller for caption more options. */
public class CaptionTogglePreferenceController extends TogglePreferenceController
        implements OnMainSwitchChangeListener {

    private final CaptioningManager mCaptioningManager;

    public CaptionTogglePreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
        mCaptioningManager = context.getSystemService(CaptioningManager.class);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public boolean isChecked() {
        return mCaptioningManager.isEnabled();
    }

    @Override
    public boolean setChecked(boolean isChecked) {
        Settings.Secure.putInt(mContext.getContentResolver(),
                Settings.Secure.ACCESSIBILITY_CAPTIONING_ENABLED, isChecked ? ON : OFF);
        return true;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);

        SettingsMainSwitchPreference pref = screen.findPreference(getPreferenceKey());
        pref.addOnSwitchChangeListener(this);
        pref.setChecked(isChecked());
    }

    @Override
    public void onSwitchChanged(Switch switchView, boolean isChecked) {
        if (isChecked != isChecked()) {
            setChecked(isChecked);
        }
    }

    @Override
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_accessibility;
    }
}