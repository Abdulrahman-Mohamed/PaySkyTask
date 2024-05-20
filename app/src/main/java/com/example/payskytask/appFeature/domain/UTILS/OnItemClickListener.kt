package com.example.payskytask.appFeature.domain.UTILS

import com.example.payskytask.appFeature.data.model.DataModelItem

interface OnItemClickListener {
    fun onClick(data: DataModelItem)
}