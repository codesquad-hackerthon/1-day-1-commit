/**
 * Ajax
 */

const ajax = {
  request({ url, method, data, reqContentType, success, error, isAsync }) {
    const xhr = new XMLHttpRequest();
    method = method.toUpperCase();
    data = (toString.call(data) === '[object Object]')? JSON.stringify(data): null;
    isAsync = (isAsync)? isAsync : true;

    xhr.open(method, url, isAsync);
    reqContentType && xhr.setRequestHeader('Content-Type', reqContentType);

    xhr.onreadystatechange = () => {
      const type = xhr.getResponseHeader('Content-Type');
      let resData = null;

      // 예외 처리
      if (xhr.readyState !== 4) { return; }
      if (xhr.status !== 200) {
        error && error(xhr);
        return;
      }

      // 응답데이터 처리
      // if (type.match(/^text/)) {
      //   resData = xhr.responseText;
      // } else if (type.match(/^application\/json/)) {
      //   resData = JSON.parse(xhr.responseText);
      // } else {
      //   throw new Error('응답은 텍스트 또는 JSON 형태여야 합니다. 현재 상태: ' + type);
      //   return;
      // }

      // 성공 콜백
      success && success(resData);
    };

    xhr.send(data);
  },
  getData({ url, data, success, error, isAsync }) {
    this.request({
      url, // 필수
      method: 'GET',
      data,
      success, // 필수
      error,
      isAsync
    });
  },
  postJSON({ url, data, success, error, isAsync }) {
    this.request({
      url,  // 필수
      method: 'POST',
      data,
      reqContentType: 'application/json',
      success, // 필수
      error,
      isAsync
    });
  }
};

export default ajax;
