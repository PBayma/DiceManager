package com.bayma.dicemanager.foundation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class IndexPreviewParameter: PreviewParameterProvider<Int> {
    override val values =  sequenceOf(0, 1)
}