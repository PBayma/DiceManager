package br.com.dice_manager.core_design_system.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class IndexPreviewParameter: PreviewParameterProvider<Int> {
    override val values =  sequenceOf(0, 1)
}