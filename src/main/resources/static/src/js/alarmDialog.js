/**
 * Alarm Dialog
 */

import ajax from './ajax.js';

const alarmDialogWrapper = document.querySelector('#alarm-dialog');

const alarmDialog = {
  opener: alarmDialogWrapper.querySelector('.opener'),
  contentBox: alarmDialogWrapper.querySelector('.content-box'),
  form: alarmDialogWrapper.querySelector('.form'),
  closeBtn: alarmDialogWrapper.querySelector('button.close'),
  userId: null,

  init({ userId }) {
    if (!this.contentBox.showModal) {
      dialogPolyfill.registerDialog(dialog);
    }
    this.userId = userId;
    this.renderTime();
    this.registerEvents();
  },
  registerEvents() {
    this.opener.addEventListener('click', () => this.show());
    this.form.addEventListener('submit', (e) => this.saveTime(e));
    this.closeBtn.addEventListener('click', () => this.close());
  },
  show() {
    this.contentBox.showModal();
  },
  close() {
    this.contentBox.close();
  },
  renderTime() {
    const alarmTimeValue = this.form.querySelector('[name="alarmTime"]').value;
    if (!alarmTimeValue) { return; }

    const alarmTimeValueArr = alarmTimeValue.split('-');
    let hour = Number(alarmTimeValueArr[0]);
    let minute = Number(alarmTimeValueArr[1]);
    const isPM = (hour > 12)? true : false;
    hour = (isPM)? hour % 12 : hour;
    hour = (hour < 10)? '0' + hour : hour;
    minute = (minute < 10)? '0' + minute : minute;

    const ampmElem = this.form.querySelector('[name="am-pm"]');
    const hourOption = this.form.querySelector(`[name="hour"] [value="${hour}"]`);
    const minuteOption = this.form.querySelector(`[name="minute"] [value="${minute}"]`);

    if (isPM) {
      ampmElem.querySelector('[value="pm"]').setAttribute('selected', 'selected');
    } else {
      ampmElem.querySelector('[value="am"]').setAttribute('selected', 'selected');
    }
    hourOption.setAttribute('selected', 'selected');
    minuteOption.setAttribute('selected', 'selected');
  },
  saveTime(e) {
    e.preventDefault();

    const ampmElem = this.form.querySelector('[name="am-pm"]');
    const hourSelectElem = this.form.querySelector('[name="hour"]');
    const minuteSelectElem = this.form.querySelector('[name="minute"]');
    
    const userId = this.userId;
    const isPM = (ampmElem.value === 'pm')? true : false;
    const hour = (isPM)? Number(hourSelectElem.value) + 12 : hourSelectElem.value;
    const minute = minuteSelectElem.value;

    const reqUrl = 'http://192.168.1.112:8080/alarm';
    const reqData = {
      "userId": `${userId}`,
      "time": `${hour}-${minute}`
    };

    ajax.postJSON({
      url: reqUrl,
      data: reqData,
      success: (res) => {
        alert('성공');
        console.log('성공', reqData, res);
        this.close();
      },
      error: (res) => {
        alert('에러');
        console.error('에러', reqData, res);
      }
    });
  }
};

export default alarmDialog;

