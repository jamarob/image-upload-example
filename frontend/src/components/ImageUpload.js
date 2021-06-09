import styled from 'styled-components/macro'
import { useRef, useState } from 'react'
import axios from 'axios'

export function ImageUpload({ onUpload }) {
  const inputRef = useRef()
  const [status, setStatus] = useState('ready')
  const [error, setError] = useState('')

  const handleSubmit = event => {
    event.preventDefault()
    setStatus('loading')
    const file = inputRef.current.files[0]
    const formData = new FormData()
    formData.set('image', file)
    axios
      .post('/photo/upload', formData)
      .then(res => res.data)
      .then(onUpload)
      .then(() => setStatus('ready'))
      .catch(error => {
        setStatus('error')
        setError(error)
      })
  }

  const isError = status === 'error'
  const isLoading = status === 'loading'

  return (
    <Wrapper onSubmit={handleSubmit}>
      {isLoading ? (
        <span>loading</span>
      ) : (
        <>
          <input type="file" ref={inputRef} />
          <button>Upload</button>
        </>
      )}
      {isError && <span>{error.message}</span>}
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
