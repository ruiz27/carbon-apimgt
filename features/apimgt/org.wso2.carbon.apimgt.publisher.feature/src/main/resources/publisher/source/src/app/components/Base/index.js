/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import React, {Component} from 'react'

import  NavBar  from './Navigation/NavBar';
import { Layout, Breadcrumb, Icon, Menu } from 'antd';
import ComposeHeader from './Header/ComposeHeader'
import Footer from './Footer/Footer'
const { Content, Sider } = Layout;



class Base extends Component {
    constructor(props){
        super(props);
        this.state = {
            collapsed: false,
            mode: 'inline'
        }
    }
    onCollapse = (collapsed) => {
        console.log(collapsed);
        this.setState({
            collapsed,
            mode: collapsed ? 'vertical' : 'inline',
        });
    }
    render() {
        return (

            <Layout style={{height:"100vh"}} >
                <ComposeHeader />
                <Content style={{ padding: '0 20px' , background: '#fff'}}>
                    <Layout style={{ padding: '0', background: '#fff' }}>
                        {this.props.leftMenu ?
                            <Sider
                                collapsed={this.state.collapsed}
                                onCollapse={this.onCollapse}
                                width={200} style={{ padding: '20px 0' ,background: '#fff' }}
                            >
                                <NavBar leftMenu={this.props.leftMenu} />
                            </Sider>

                            : <div />}
                        <Content style={{ padding: '20px 0', minHeight: 280 }}>
                            {this.props.children}
                        </Content>
                    </Layout>
                </Content>
                <Footer />
            </Layout>

        );
    }
}

export default Base;