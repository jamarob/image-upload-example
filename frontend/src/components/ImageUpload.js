import styled from 'styled-components/macro'

export function ImageUpload() {
  return (
    <Wrapper>
      <input type="file" />
      <button>Upload</button>
    </Wrapper>
  )
}

const Wrapper = styled.form`
  position: fixed;
  bottom: 24px;
  left: 24px;
  right: 24px;
  border: 1px solid #efefef;
  background: white;
  border-radius: 16px;
  padding: 8px;
  box-shadow: 0px 1px 1px #444;
`
