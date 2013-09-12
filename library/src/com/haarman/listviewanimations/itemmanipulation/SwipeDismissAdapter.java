/*
 * Copyright 2013 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haarman.listviewanimations.itemmanipulation;

import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.haarman.listviewanimations.BaseAdapterDecorator;

/**
 * Adds an option to swipe items in a ListView away. This does nothing more than
 * setting a new SwipeDismissListViewTouchListener to the ListView. You can
 * achieve the same effect by calling listView.setOnTouchListener(new
 * SwipeDismissListViewTouchListener(...)).
 */
public class SwipeDismissAdapter extends BaseAdapterDecorator {

	private OnDismissCallback mCallback;
    private SwipeDismissListViewTouchListener mOnTouchListener;

    public SwipeDismissAdapter(BaseAdapter baseAdapter, OnDismissCallback callback) {
		super(baseAdapter);
		mCallback = callback;
	}

	@Override
	public void setAbsListView(AbsListView listView) {
		super.setAbsListView(listView);
        mOnTouchListener = new SwipeDismissListViewTouchListener(listView, mCallback);
        mOnTouchListener.setIsParentHorizontalScrollContainer(isParentHorizontalScrollContainer());
        mOnTouchListener.setTouchChild(getTouchChild());
        listView.setOnTouchListener(mOnTouchListener);
	}

    @Override
    public void setIsParentHorizontalScrollContainer(boolean isParentHorizontalScrollContainer) {
        super.setIsParentHorizontalScrollContainer(isParentHorizontalScrollContainer);
        if (mOnTouchListener != null) {
            mOnTouchListener.setIsParentHorizontalScrollContainer(isParentHorizontalScrollContainer);
        }
    }

    @Override
    public void setTouchChild(int childResId) {
        super.setTouchChild(childResId);
        if (mOnTouchListener != null) {
            mOnTouchListener.setTouchChild(childResId);
        }
    }
}
