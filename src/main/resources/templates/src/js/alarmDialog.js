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

