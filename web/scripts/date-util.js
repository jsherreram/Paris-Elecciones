/**
 * Funciones utilitarias para manejo de fechas.
 * Dependencias: date.js. datejs Version: 1.0 Alpha-1 
 * 
 */
function calcularFechaHoy() {
    var now = Date.today();
    var res = {};
    res.fechaInicial = now.toString(formulario.global.formatoFecha);
    res.fechaFinal = now.toString(formulario.global.formatoFecha);
    return res;
}

function calcularFechaSemana() {
    var res = {};
    var start = Date.parse("sunday");
    var end = Date.parse("saturday");
    res.fechaInicial = start.toString(formulario.global.formatoFecha);
    res.fechaFinal = end.toString(formulario.global.formatoFecha);
    return res;
}

function calcularFechaMes() {
    var now = Date.today();
    var res = {};
    var start = now.clone().moveToFirstDayOfMonth();
    var end = now.clone().moveToLastDayOfMonth();
    res.fechaInicial = start.toString(formulario.global.formatoFecha);
    res.fechaFinal = end.toString(formulario.global.formatoFecha);
    return res;
}

function calcularFechaAnio() {
    var now = Date.today();
    var res = {};
    var start = now.clone().set({day: 1, month: 0});
    var end = now.clone().set({day: 1, month: 11});
    res.fechaInicial = start.toString(formulario.global.formatoFecha);
    res.fechaFinal = end.toString(formulario.global.formatoFecha);
    return res;
}

