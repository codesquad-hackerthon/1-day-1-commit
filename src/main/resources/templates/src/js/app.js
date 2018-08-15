/**
 * App
 */

import alarmDialog from './alarmDialog.js';

const app = {
  userId: location.search.replace('?', '').split('userId=')[1],
  init({ alarmDialog }) {
    alarmDialog.init({ userId: this.userId });
  }
};

document.addEventListener('DOMContentLoaded', () => {
  app.init({ alarmDialog });
});

