import React, { Component } from 'react';
import './App.css';

import { Input, Button} from 'antd';
import $ from  'jquery';

const style = {
    margin : "25px"
};


class App extends Component {

  handBtnClick = (event) => {
    console.log($("#file")[0].files);
    let fileList = $("#file")[0].files;

    const form = new FormData();
    form.append('file', fileList[0]);

    $.ajax({
        // url: 'http://23.95.213.229:9091/upload',
        url: 'http://localhost:8090/upload',
        type: "POST",
        data: form,
        cache:false,
        processData:false,
        contentType:false,
        success: function (data, textStatus) {
            console.log("data: " + data)
            console.log("textStatus: " + textStatus)
            alert(data);
        },
        error: function (xhr, textStatus) {
            console.log("xhr: " + xhr)
            console.log("textStatus: " + textStatus)
            alert(xhr);
        }
    });
  };

  render() {
    return (
      <div className="App" style={style}>
          <Input id="file" name="file" type="file" style={{width:"20%"}}/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <Button type="primary" onClick={this.handBtnClick}>点击按钮</Button>
          <div>
              <a href=""></a>
          </div>
      </div>
    );
  };
  
}

export default App;
