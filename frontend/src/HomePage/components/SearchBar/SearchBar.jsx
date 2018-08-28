import React, { Component } from 'react'
import styled from 'styled-components'

const Input = styled.input`
  border: none;
  width: 100%;
  height: 40px;
  box-sizing: border-box; 
  font-size: 16px;
  border-bottom: 2px solid #2980b9;
  border-top: 1px solid #2980b9;
  padding-left: 5px;
`

export class SearchBar extends Component {
  render() {
    return (
      <Input type="text" placeholder="Search..." />
    )
  }
}

export default SearchBar
