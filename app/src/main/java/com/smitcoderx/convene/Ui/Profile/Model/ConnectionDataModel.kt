package com.smitcoderx.convene.Ui.Profile.Model

import com.google.android.datatransport.cct.StringMerger

data class ConnectionDataModel(
    var id: String,
    var type: String,
    var name: String,
    var desc: String,
    var action: String,
    var image: String,
    var time: String,
)
