/**
 * App
 */

import alarmDialog from './alarmDialog.js';

const app = {
  userId: location.search.replace('?', '').split('userId=')[1],
  loginForm: document.querySelector('#login-form'),
  init({ alarmDialog }) {
    alarmDialog.init({ userId: this.userId });

    this.loginForm.addEventListener('submit', (e) => {
      e.preventDefault();

      const userId = this.loginForm.querySelector('[name="userId"]').value;
      location.href = `/commits?userId=${userId}`;
    });
  }
};

document.addEventListener('DOMContentLoaded', () => {
  app.init({ alarmDialog });
});

