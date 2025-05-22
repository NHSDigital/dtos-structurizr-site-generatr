package nl.avisi.structurizr.site.generatr.site.views

import kotlinx.html.*
import nl.avisi.structurizr.site.generatr.site.model.TableViewModel
import nl.avisi.structurizr.site.generatr.site.model.CellWidth

fun FlowContent.table(viewModel: TableViewModel) {
    table (classes = "nhsuk-table") {
        thead( classes = "nhsuk-table__head") {
            viewModel.headerRows.forEach {
                row(it)
            }
        }
        tbody {
            viewModel.bodyRows.forEach {
                row(it)
            }
        }
    }
}

private fun THEAD.row(viewModel: TableViewModel.RowViewModel) {
    tr {
        viewModel.columns.forEach {
            cell(it)
        }
    }
}

private fun TBODY.row(viewModel: TableViewModel.RowViewModel) {
    tr {
        viewModel.columns.forEach {
            cell(it)
        }
    }
}

private fun TR.cell(viewModel: TableViewModel.CellViewModel) {
    when (viewModel) {
        is TableViewModel.TextCellViewModel -> {
            if (viewModel.isHeader)
                th(classes = "nhsuk-table__header") {
                    scope = ThScope.col
                    +viewModel.title
                }
            else
                td(classes = "nhsuk-table__cell") { +viewModel.title  }
        }
        is TableViewModel.LinkCellViewModel -> {
            if (viewModel.isHeader)
                th(classes = "nhsuk-table__header")  { link(viewModel.link) }
            else
                td(classes = "nhsuk-table__cell")  { link(viewModel.link) }
        }
        is TableViewModel.ExternalLinkCellViewModel ->
            if (viewModel.isHeader)
                th(classes = "nhsuk-table__header") { externalLink(viewModel.link) }
            else
                td(classes = "nhsuk-table__cell") { externalLink(viewModel.link) }
    }
}
