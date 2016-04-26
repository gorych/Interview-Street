$(document).ready(function () {

    var chartOptions = {
        lang: {
            printChart: 'Распечатать график',
            downloadPNG: 'Скачать в PNG',
            downloadJPEG: 'Скачать в JPEG',
            downloadPDF: 'Скачать в PDF',
            downloadSVG: 'Скачать в SVG',
            contextButtonTitle: 'Контекстное меню'
        },
        chart: {
            backgroundColor: '#f5f5f5'
        },
        credits: {
            enabled: false
        },
        title: {
            text: undefined
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 'Ответило, чел'
            }
        },
        xAxis: {
            title: {
                text: 'Ответ респондента'
            },
            labels: {
                enabled: false
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                },
                showInLegend: true
            }
        }
    };

    function collectData() {
        var data = [];

        $("#datatable").find("tbody>tr").each(function() {
            var answer = $(this).find('th:first').text();
            var peopleCount = $(this).find('td:nth-child(2)').text();

            data.push({
                "name": answer,
                "y": parseFloat(peopleCount)
            });
        });

        return data;
    }

    $(".col-chart-btn, .pie-chart-btn").click(function () {
        var $body = $(this).parents(".collapsible-body");
        $body.find("table").addClass("hide");
        $body.find(".chart-container").removeClass("hide");

        if ($(this).hasClass("pie-chart-btn")) {
            chartOptions.chart.type = "pie";

            delete chartOptions['data'];

            chartOptions.series =  [{
              name: 'Ответило',
              colorByPoint: true,
              data: collectData()
            }];

            chartOptions.tooltip = {
                pointFormat: '{series.name}: <b>[{point.percentage:.1f}%, {point.y} чел]</b>'
            };
        } else {
            chartOptions.chart.type = "column";

            delete chartOptions['series'];

            chartOptions.data = {
                table: 'datatable',
                endColumn: 1,
                endRow: $(this).parents(".collapsible-body").find("table>tbody>tr").length,
                switchRowsAndColumns: true
            };

            chartOptions.tooltip = {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        "Ответило человек: " + this.point.y;
                }
            };
        }

        $body.find(".chart-container").highcharts(chartOptions);
    });

    $(".table-btn").click(function () {
        var $body = $(this).parents(".collapsible-body");

        $body.find(".chart-container").addClass("hide");
        Materialize.fadeInImage($body.find("table").removeClass("hide"));
    });
});