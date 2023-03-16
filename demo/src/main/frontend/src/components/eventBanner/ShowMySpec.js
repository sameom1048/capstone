import React, { useEffect, useState } from "react";
import axios from "axios";

function ShowMySpec() {
    const [data, setData] = useState(null);
    const [cpuInfo, setCpuInfo] = useState('');
    const [gpuInfo, setGpuInfo] = useState('');
    const [CPU, setCPU] = useState('');
    const [GPU, setGPU] = useState('');
    const [RAM, setRAM] = useState('');

    useEffect(() => {
        // CPU 정보 가져오기
        const cpu = navigator.hardwareConcurrency;
        setCpuInfo(`CPU 모델명: ${cpu}`);

        // WebGL을 사용하여 GPU 정보 가져오기
        const canvas = document.createElement('canvas');
        const gl = canvas.getContext('webgl') || canvas.getContext('experimental-webgl');
        if (gl) {
            const info = gl.getExtension('WEBGL_debug_renderer_info');
            if (info) {
                const gpu = gl.getParameter(info.UNMASKED_RENDERER_WEBGL);
                setGpuInfo(`GPU 모델명: ${gpu}`);
            } else {
                setGpuInfo('GPU 정보를 가져올 수 없습니다.');
            }
        } else {
            setGpuInfo('WebGL을 지원하지 않는 브라우저입니다.');
        }
    }, []);

    const handleClick = async () => {
        try {
            const response = await axios.get("http://localhost:12000");
            console.log("받은 데이터:", response.data);
            setData(response.data);
        } catch (error) {
            console.error(error);
            // TODO: 오류 처리 로직을 추가
        }
    };

    const handleInputChange = event => {
        if (event.target.name === "CPU") {
            setCPU(event.target.value);
        } else if (event.target.name === "GPU") {
            setGPU(event.target.value);
        } else if (event.target.name === "RAM") {
            setRAM(event.target.value);
        }
    }

    const handleSubmit = event => {
        event.preventDefault();
        const userData = {
            CPU: CPU,
            GPU: GPU,
            RAM: RAM
        };
        axios.post('http://localhost:12000/api/insert', userData)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            });
    }

    return (
        <div>
            <button onClick={handleClick}>Send Request to Spring Boot Server</button>
            {data && (
                <ul>
                    {data.map((item) => (
                        <li key={item.id}>{item.name}</li>
                    ))}
                </ul>
            )}
            {cpuInfo !== null && <p>CPU 정보: {cpuInfo}</p>}
            {gpuInfo !== null && <p>GPU 정보: {gpuInfo}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="text">CPU: </label>
                    <input type="text" id="CPU" name="CPU" onChange={handleInputChange} />
                </div>
                <div>
                    <label htmlFor="text">GPU: </label>
                    <input type="text" id="GPU" name="GPU" onChange={handleInputChange} />
                </div>
                <div>
                    <label htmlFor="text">RAM: </label>
                    <input type="text" id="RAM" name="RAM" onChange={handleInputChange} />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default ShowMySpec;