// https://github.com/uxitten/polyfill/blob/master/string.polyfill.js
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/repeat
if (!String.prototype.padStart) {
  String.prototype.padStart = function padStart(targetLength, padString) {
    targetLength = targetLength >> 0; //floor if number or convert non-number to 0;
    padString = String(padString || ' ');
    if (this.length > targetLength) {
      return String(this);
    } else {
      targetLength = targetLength - this.length;
      if (targetLength > padString.length) {
        padString += padString.repeat(targetLength / padString.length); //append to original to ensure we are longer than needed
      }
      return padString.slice(0, targetLength) + String(this);
    }
  };
}

Date.prototype.format = function () {
  var year = this.getFullYear()
  var month = (this.getMonth() + 1).toString().padStart(2, '0')
  var day = this.getDate().toString().padStart(2, '0')
  return `${year}${month}${day}`
}

// http://stackoverflow.com/questions/2901102/how-to-print-a-number-with-commas-as-thousands-separators-in-javascript
function numberWithCommas(value) {
  var parts = value.toString().split(".");
  parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  return parts.join(".");
}

/**
 * get the first date for the specified date according to local time. 
 * @param {date} date 
 */
function firstDayOfWeek(date = new Date()) {
  var _date = new Date(date)
  var dayOfWeek = _date.getDay()
  var day = _date.getDate()
  var firstDay = day - dayOfWeek
  _date.setDate(firstDay)
  return _date
}

/**
 * get the last date for the specified date according to local time. 
 * @param {*} date 
 */
function lastDayOfWeek(date = new Date()) {
  var _date = new Date(date)
  var dayOfWeek = _date.getDay()
  var day = _date.getDate()
  // week (0-6) 
  var timespan = 6 - dayOfWeek
  var lastDay = day + timespan
  _date.setDate(lastDay)
  return _date
}

/**
 * get the first date for the specified date according to local time. 
 * @param {*} date 
 */
function firstDayOfMonth(date = new Date()) {
  var _date = new Date(date)
  _date.setDate(1)
  return _date
}

/**
 * get the last date for the specified date according to local time. 
 * @param {*} date 
 */
function lastDayOfMonth(date = new Date()) {
  var _date = new Date(date)
  var month = _date.getMonth() // current month
  var nextMonth = month + 1
  // 如果 dayValue 超出了月份的合理范围，setDate 将会相应地更新 Date 对象。
  // 例如，如果为 dayValue 指定0，那么日期就会被设置为上个月的最后一天。
  _date.setMonth(nextMonth, 0)
  return _date
}

/**
 * get the first date for the specified date according to local time. 
 * @param {*} date 
 */
function firstDayOfQuarter(date = new Date()) {
  var _date = new Date(date)
  var month = _date.getMonth() // current month

  if (month >= 0 && month <= 2) {
    _date.setMonth(0, 1)
  }

  if (month >= 3 && month <= 5) {
    _date.setMonth(3, 1)
  }

  if (month >= 6 && month <= 8) {
    _date.setMonth(6, 1)
  }

  if (month >= 9 && month <= 11) {
    _date.setMonth(9, 1)
  }

  return _date
}

/**
 * get the last date for the specified date according to local time. 
 * @param {*} date 
 */
function lastDayOfQuarter(date = new Date()) {
  var _date = new Date(date)
  var month = _date.getMonth() // current month

  // 如果 dayValue 超出了月份的合理范围，setDate 将会相应地更新 Date 对象。
  // 例如，如果为 dayValue 指定0，那么日期就会被设置为上个月的最后一天。  
  if (month >= 0 && month <= 2) {
    _date.setMonth(3, 0)
  }

  if (month >= 3 && month <= 5) {
    _date.setMonth(6, 0)
  }

  if (month >= 6 && month <= 8) {
    _date.setMonth(9, 0)
  }

  if (month >= 9 && month <= 11) {
    _date.setMonth(12, 0)
  }

  return _date
}

function firstDayOfYear(date = new Date()) {
  var _date = new Date(date)
  _date.setMonth(0, 1)
  return _date
}

function lastDayOfYear(date = new Date()) {
  var _date = new Date(date)
  _date.setMonth(12, 0)
  return _date
}

/**
 * 
 * @param {*} yearWeek 2017第10周
 */
function getDayByWeek(yearWeek) {
  let arr = yearWeek.match(/\d+/g);
  let year = +arr[0],
    week = +arr[1];
  let date = new Date(year, 0, 1)
  let weekTime = weeksToMilliseconds(week)

  let targetTime = date.getTime() + weekTime;

  let result = date.setTime(targetTime);

  return new Date(result)
}

function weeksToMilliseconds(week) {
  return 1000 * 60 * 60 * 24 * 7 * (week - 1);
}

function getYesterday() {
  var todayTimeStamp = +new Date; // Unix timestamp in milliseconds
  var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
  var diff = todayTimeStamp - oneDayTimeStamp;
  var yesterdayDate = new Date(diff);
  var month = (yesterdayDate.getMonth() + 1).toString().padStart(2, '0');
  var day = yesterdayDate.getDate().toString().padStart(2, '0');

  var yesterdayString = yesterdayDate.getFullYear() + '-' + month + '-' + day;
  return yesterdayString
}

export default {
  firstDayOfWeek,
  lastDayOfWeek,
  firstDayOfMonth,
  lastDayOfMonth,
  firstDayOfQuarter,
  lastDayOfQuarter,
  firstDayOfYear,
  lastDayOfYear,
  getDayByWeek,
  numberWithCommas,
  getYesterday
}
