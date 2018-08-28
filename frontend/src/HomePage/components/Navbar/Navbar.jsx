import React, { Component } from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom';

const Container = styled.div`
  width: 100%;
  height: 60px;
  border-bottom: 2px solid #2980b9;
  display: flex;
  align-items: center;
  justify-content: space-between;
`
const NavbarItem = styled.div`
  color: #2980b9;
  font-size: 120%;
  text-transform: uppercase;
  margin-left: 70px;
  font-size: 18px;
  font-weight: bold;
  letter-spacing: 1px;
`
const NavbarMenu = styled.div`
  display: inline-block;
  cursor: pointer;
  margin-right: 70px;
  color: #2980b9;
  position: relative;
`
const Dot = styled.div`
  width: 5px;
  height: 5px;
  border-radius: 100%;
  background-color: #2980b9;
  margin: 6px 0;  
  transition: 0.4s;
`
const Menu = styled.div`
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  position: absolute;
  top: 0;
  right: 20px;
  width: 120px;
  height: 60px;
  border-radius: 4px;
  background-color: #fefefe;
  z-index: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
`
const menuLinkStyles = {
  textDecoration: 'none',
  color: '#2980b9',
  letterSpacing: '1px'
}

export class Navbar extends Component {
  constructor() {
    super();

    this.state = {
      toggled: false,
    }
  }
  toggleMenu() {
    this.setState({ toggled: !this.state.toggled })
  }
  render() {
    return (
      <nav>
        <Container>
          <NavbarItem>
            Encrypted Chat
          </NavbarItem>
          <NavbarItem>
            <NavbarMenu onClick={this.toggleMenu.bind(this)}>
              <Dot></Dot>
              <Dot></Dot>
              <Dot></Dot>
              {this.state.toggled &&
                <Menu>
                  <Link style={menuLinkStyles} to="/login">Logout</Link>
                </Menu>
              }
            </NavbarMenu>
          </NavbarItem>
        </Container>
      </nav >
    )
  }
}

export default Navbar
