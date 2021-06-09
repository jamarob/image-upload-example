import styled from 'styled-components/macro'
import { useRef } from 'react'
import axios from 'axios'
import { useMutation, useQueryClient } from 'react-query'

export function ImageUpload() {
  const inputRef = useRef()
  const queryClient = useQueryClient()
  const {
    isError,
    isLoading,
    error,
    mutate: uploadPhoto,
  } = useMutation(
    file => {
      const formData = new FormData()
      formData.set('image', file)
      return axios.post('/photo/upload', formData).then(res => res.data)
    },
    {
      onSuccess: () => {
        queryClient.invalidateQueries('photos')
      },
    }
  )

  const handleSubmit = event => {
    event.preventDefault()
    const file = inputRef.current.files[0]
    uploadPhoto(file)
  }

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
